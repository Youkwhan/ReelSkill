import { useEffect, useState } from 'react';

export default function useDarkMode() {
  const [darkMode, setDarkMode] = useState(false);

  useEffect(() => {
    document.documentElement.setAttribute(
      'data-bs-theme',
      darkMode ? 'dark' : 'light'
    );
  }, [darkMode]);

  return (evt) => setDarkMode(evt.target.checked);
}
