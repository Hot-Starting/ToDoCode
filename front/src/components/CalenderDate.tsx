import styles from "../Components.module.scss"
import { DateType } from "./MainCalender"
// type number 형태로 props 전달 받은 숫자를 표기

interface CalenderProps {
  date: DateType,
}

export default function calenderMonth( {date} : CalenderProps) {
  
  const { index, day, css } = date

  return (
    <div className={styles.calenderDate}>
      {
        index === 0 && css.color === "black"?
        <div className={styles.dayDate} style={{color : "red"}}>
          {
            day.slice(-2, -1) === '0' ? day.slice(-1) : day.slice(-2)
          }
        </div>
        :
        index === 0 && css.color === "gray" ?
        <div className={styles.dayDate} style={{color : "lightpink"}}>
          {
            day.slice(-2, -1) === '0' ? day.slice(-1) : day.slice(-2)
          }
        </div>
        :
        index !== 0 && css.color === "pink" ?
        <div className={styles.dayDate} style={{color : "pink"}}>
          {
            day.slice(-2, -1) === '0' ? day.slice(-1) : day.slice(-2)
          }
        </div>
        :
        index !== 0 && css.color === "gray" ?
        <div className={styles.dayDate} style={{color : "gray"}}>
          {
            day.slice(-2, -1) === '0' ? day.slice(-1) : day.slice(-2)
          }
        </div>
        :
        <div className={styles.dayDate} style={{color : "black"}}>
          {
            day.slice(-2, -1) === '0' ? day.slice(-1) : day.slice(-2)
          }
        </div>
      }
    </div>
  );
}