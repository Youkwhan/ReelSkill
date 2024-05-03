import { useContext } from 'react';
import { AuthContext } from '../providers/AuthProvider';

export default function useAuthContext() {
  return useContext(AuthContext);
}
