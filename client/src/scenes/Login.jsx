import { useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { authenticate } from '../api/userApi';
import { useAuthContext } from '../hooks';

function Login() {
  const [credentials, setCredentials] = useState({
    username: '',
    password: '',
  });
  const [badCredentials, setBadCredentials] = useState(false);
  const { login } = useAuthContext();
  const navigate = useNavigate();

  function handleChange(evt) {
    const next = { ...credentials };
    next[evt.target.name] = evt.target.value;
    setCredentials(next);
  }

  function handleSubmit(evt) {
    evt.preventDefault();
    authenticate(credentials)
      .then((user) => {
        login(user);
        navigate('/');
      })
      .catch(() => setBadCredentials(true));
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      {badCredentials && (
        <div className="alert alert-danger">Bad Credentials.</div>
      )}
      <div className="mb-3">
        <label htmlFor="username" className="form-label">
          Username
        </label>
        <input
          type="text"
          id="username"
          name="username"
          className="form-control"
          value={credentials.username}
          onChange={handleChange}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="password" className="form-label">
          Password
        </label>
        <input
          type="password"
          id="password"
          name="password"
          className="form-control"
          value={credentials.password}
          onChange={handleChange}
        />
      </div>
      <div>
        {/* <Link to="/" className="btn btn-warning me-2">
          Cancel
        </Link> */}
        <button type="submit" className="btn btn-primary">
          Login
        </button>
      </div>
    </form>
  );
}

export default Login;
