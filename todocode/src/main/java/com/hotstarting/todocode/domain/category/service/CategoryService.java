package com.hotstarting.todocode.domain.category.service;

import com.hotstarting.todocode.domain.category.domain.Category;
import com.hotstarting.todocode.domain.category.dto.CategoryAddRequest;
import com.hotstarting.todocode.domain.category.dto.CategoryAddResponse;
import com.hotstarting.todocode.domain.category.dto.CategoryListResponse;
import com.hotstarting.todocode.domain.category.repository.CategoryRepository;
import com.hotstarting.todocode.domain.member.domain.Member;
import com.hotstarting.todocode.domain.member.repository.MemberRepository;
import com.hotstarting.todocode.domain.memberList.domain.MemberList;
import com.hotstarting.todocode.domain.memberList.repository.MemberListRepository;
import com.hotstarting.todocode.global.exception.CustomException;
import com.hotstarting.todocode.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final MemberListRepository memberListRepository;

    public CategoryListResponse categoryList(String socialId) {
        //사용자 조회
        Member member = memberRepository.findBySocialId(socialId);
        if (member == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }
        
        // 카테고리 조회

        return null;
    }
    public CategoryAddResponse categoryAdd(String socialId, CategoryAddRequest categoryAddRequest) {
        //사용자 조회
        Member member = memberRepository.findBySocialId(socialId);
        if (member == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        // 카테고리 등록
        String categoryName = categoryAddRequest.getName();
        String emoticonUrl = categoryAddRequest.getEmoticonUrl();
        Category category = Category.of(categoryName, emoticonUrl);
        categoryRepository.save(category);

        // 멤버 리스트 추가
        List<String> memberList = categoryAddRequest.getMemberEmail();
        for(String memberEmail: memberList) {
            Member m = memberRepository.findByEmail(memberEmail);
            MemberList memberList1 = MemberList.of(m, category);
            memberListRepository.save(memberList1);
        }

        return CategoryAddResponse.ofCategoryAddResponse(category);
    }
}
