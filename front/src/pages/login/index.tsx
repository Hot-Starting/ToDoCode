export default function Login() {
  const redirectUrl = "http://localhost:3000";

  const googleUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.NEXT_PUBLIC_GOOGLE_ID}&redirect_uri=${redirectUrl}&response_type=code&scope=email profile`;

  const githubUrl = `/github/login/oauth/authorize?client_id=${process.env.NEXT_PUBLIC_GITHUB_ID}&redirect_url=${redirectUrl}`;

  return (
    <>
      <a href={googleUrl}>구글</a>
      <a href={githubUrl}>깃허브</a>
    </>
  );
}
