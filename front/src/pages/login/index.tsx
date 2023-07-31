export default function Login() {
  const redirectUrl = "http://localhost:3000";

  // const googleUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.NEXT_PUBLIC_GOOGLE_ID}&redirect_uri=${redirectUrl}&response_type=code&scope=email profile`;
  const googleUrl =
    "http://localhost:8080/oauth2/authorization/GOOGLE?redirect_uri=http://localhost:3000/oauth/redirect";

  // const githubUrl = `/github/login/oauth/authorize?client_id=${process.env.NEXT_PUBLIC_GITHUB_ID}&redirect_url=${redirectUrl}`;
  const githubUrl =
    "http://localhost:8080/oauth2/authorization/GITHUB?redirect_uri=http://localhost:3000/oauth/redirect";

  return (
    <>
      <a href={googleUrl}>구글</a>
      <a href={githubUrl}>깃허브</a>
    </>
  );
}
