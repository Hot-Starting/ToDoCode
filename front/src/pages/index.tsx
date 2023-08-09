import Link from "next/link";
import { useState } from "react";

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
    </div>
  );
}
