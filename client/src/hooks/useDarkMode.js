import { useEffect, useState } from 'react';

export default function useDarkMode() {
  const [darkMode, setDarkMode] = useState(false);

  useEffect(() => {
    document.documentElement.setAttribute(
      'data-bs-theme',
      darkMode ? 'dark' : 'light'
    );
    document.documentElement.style.setProperty(
      '--border-color',
      darkMode ? '#fff' : '#000' // border
    );
  }, [darkMode]);

  return (evt) => setDarkMode(evt.target.checked);
}
