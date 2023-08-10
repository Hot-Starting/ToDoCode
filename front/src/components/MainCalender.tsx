import moment from 'moment';
import type { NextPage } from 'next';
import { useEffect, useState } from 'react';
import Month from "./CalenderMonth";

import Day from "./CalenderDate";
import styles from "../Components.module.scss";

export type DateType = {
  index: number,
  day: string,
  work: string,
  tardy: string,
  css: {
    color: string,
    bgColor: string,
    display: string,
  },
  func: {
    onClick: boolean,
  }
}

const mainCalender: NextPage = () => {
  const [getMoment, setMoment] = useState(moment()); // 오늘
  const today = getMoment;
  const date = new Date();
  const thisMonth = date.getMonth() + 1;

  const todayFirstWeek = today.clone().startOf('month').week(); // 이번달의 첫째 주
  const todayLaskWeek = today.clone().endOf('month').week() === 1 ? 53 : today.clone().endOf('month').week(); // 이번달의 마지막 주

  const [dateArray, setDateArray] = useState<any>([]); // 날짜들이 가지는 상태값들 모아둔 배열
  const dayArray = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

  useEffect(() => {
    // 고유 인덱스 번호
    let index = 0;

    // 이번 달 배열에 담기
    let week = todayFirstWeek;
    let result = []; // 이번 달 배열
    for(week; week <= todayLaskWeek; week++) {
      let weekArray: any[] = []; // 주 별로 배열에 담기
      
      for(var i=0; i < 7; i++){
        let days = today.clone().startOf('year').week(week).startOf('week').add(i, 'day'); // 그날의 시간 정보
        if(moment().format('YYYYMMDD') === days.format('YYYYMMDD')) { // 현재 날짜인 경우
          weekArray.push({
            index: i,
            day: days.format('YYYY-MM-DD'),
            work: "",
            tardy: "",
            css: {
              color: "pink",
              bgColor: '#f6f6f6',
              display: "",
            },
            func: {
              onClick: true,
            }
          });
        }
        else if(days.format('MM') !== today.format('MM')) { // 현재 월이 아닐 경우 클릭 옵션 안주고 안 보여줌
          weekArray.push({
            index: i,
            day: days.format('YYYY-MM-DD'),
            work: "",
            tardy: "",
            css: {
              color: "gray",
              bgColor: '#f6f6f6',
              display: "",
            },
            func: {
              onClick: false,
            }
          });
        }
        else {
          weekArray.push({
            index: i,
            day: days.format('YYYY-MM-DD'),
            work: "",
            tardy: "",
            css: {
              color: "black",
              bgColor: '#f6f6f6',
              display: "",
            },
            func: {
              onClick: false,
            }
          });
        }
        index++;
      }
      result.push(weekArray);
    }
    setDateArray(result);
  }, []);

  return (
    <div className={styles.calender}>
      <Month month={thisMonth} />
      {/* <div className={styles.dayArray} >
      {
        dayArray.map((day, idx) => (
          <div className={styles.calenderDay} key={idx} style={{color : day === "Sun" ? "red" : "black"}}>
            {day}
          </div>
        ))
      }
      </div> */}
      <div className={styles.calenderMain}>
        {
          dayArray.map((day, idx) => (
            <div className={styles.calenderDay} key={idx} style={{color : day === "Sun" ? "red" : "black"}}>
              {day}
            </div>
          ))
        }
        {
          dateArray.map((week : Array<[]>) => (
            week.map((day: any, index) => (
              index === 0 ?
              <Day date={day} key={index}/>
              :
              <Day date={day} key={index}/>
            ))
          ))
        }
      </div>
    </div>
  );
}

export default mainCalender;
