import Link from "next/link";
import { useState } from "react";
import MainCalender from "../components/MainCalender"

export default function Home() {
  return (
    <div>
      <Link
        href={{
          pathname: "/login",
        }}
      >
        로그인하러가기
      </Link>
      {/* 왼쪽 달력부분의 월 표기 */}
      {/* <Month month={1}/>*/}
      {/* <Date date={3} className={"day"} /> */}
      <MainCalender />
    </div>
  );
}
