import {useMemo} from 'react';

type Props = {
    children:React.ReactNode;
};

export default function ThemeProvider({children} : Props){
    const memoizedValue = useMemo(
        () => ({
            palette:palette('light'),
            shadows:shadows('light'),
            customShadows:customShadows('light'),
            shape:{borderRadius:8},
            typography,
        }),[]
    );
}