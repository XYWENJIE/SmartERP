import './App.css'
import ThemeProvider from './theme';
import Router from './routes/sections.tsx';
import { useScrollToTop } from './hooks/use-scroll-to-top.ts';

function App() {

  useScrollToTop();

  return (
    <ThemeProvider>
      <Router/>
    </ThemeProvider>
  )
}

export default App
