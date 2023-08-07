export default function Login() {
  const redirectUrl = "http://localhost:3000/login/callback";

  // const googleUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.NEXT_PUBLIC_GOOGLE_ID}&redirect_uri=${redirectUrl}&response_type=code&scope=email profile`;
  const googleUrl = `http://localhost:8080/oauth2/authorization/google?redirect_uri=${redirectUrl}`;

  // const githubUrl = `/github/login/oauth/authorize?client_id=${process.env.NEXT_PUBLIC_GITHUB_ID}&redirect_url=${redirectUrl}`;
  const githubUrl = `http://localhost:8080/oauth2/authorization/github?redirect_uri=${redirectUrl}`;
  // const githubUrl = `http://localhost:3000/login/callback?token=IamToken`;

  return (
    <>
      <div>
        <a href={googleUrl}>구글</a>
        <br />
        <a href={githubUrl}>깃허브</a>
      </div>
    </>
  );
}
