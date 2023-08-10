import styles from "../Components.module.scss"
import { useState, useEffect } from "react"

// type number 형태로 props 전달 받은 숫자를 표기

interface CalenderProps {
  month?: number
}

export default function calenderMonth(props : CalenderProps) {
  
  const { month } = props
  const [calenderMonth, setCalenderMonth] = useState("")
  
  useEffect(() => {
    if(month){
      if(month < 10) setCalenderMonth("0" + String(month))
      else setCalenderMonth(String(month))
    }
  }, [])

  return (
    <div className={styles.largeMonth}>
      {calenderMonth}
    </div>
  );
}