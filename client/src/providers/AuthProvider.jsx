import { createContext, useEffect, useState } from 'react';

export const AuthContext = createContext();

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [restoreLoginAttemptCompleted, setRestoreLoginAttemptCompleted] =
    useState(false);

  useEffect(() => {
    const userAccount = localStorage.getItem('userAccount')
      ? JSON.parse(localStorage.getItem('userAccount'))
      : null;
    if (userAccount) {
      login(userAccount);
    }
    setRestoreLoginAttemptCompleted(true);
  }, []);

  function login(userAccount) {
    localStorage.setItem('userAccount', JSON.stringify(userAccount));
    setUser(userAccount);
  }

  function logout() {
    setUser(null);
    localStorage.removeItem('userAccount');
  }

  const state = {
    user: user ? { ...user } : null,
    login,
    logout,
  };

  if (!restoreLoginAttemptCompleted) {
    return null;
  }

  return <AuthContext.Provider value={state}>{children}</AuthContext.Provider>;
}

export default AuthProvider;
