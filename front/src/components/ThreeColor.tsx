import styles from "../Components.module.scss"

export default function ThreeColor() {
  return (
    <div className={styles.threeColor}>
      <div className={styles.redBall}></div>
      <div className={styles.yellowBall}></div>
      <div className={styles.greenBall}></div>                  
    </div>
  );
}