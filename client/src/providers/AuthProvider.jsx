import { createContext, useState } from 'react';

export const AuthContext = createContext();

function AuthProvider({ children }) {
  const [user, setUser] = useState();

  function login(userAccount) {
    setUser(userAccount);
  }

  function logout() {
    setUser();
  }

  const state = {
    user,
    login,
    logout,
  };

  return <AuthContext.Provider value={state}>{children}</AuthContext.Provider>;
}

export default AuthProvider;
