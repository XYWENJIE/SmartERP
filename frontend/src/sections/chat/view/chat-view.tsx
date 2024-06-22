import {Box, Button, Container, Stack, Typography} from "@mui/material";

export default function ChatView(){
    return (
        <>
            <Container>
                <Typography variant="h4">
                    Hello word
                </Typography>
                <Stack direction="row" spacing={2}>
                    <Box>
                        <Button></Button>
                        <Stack>
                            <Stack direction="row" spacing={3}>
                                <span>Hello Word</span>
                                <Button>X</Button>
                                <Button>X</Button>
                            </Stack>
                            <Box>
                                <div>Hello Word</div>
                            </Box>
                            <Box>
                                <div>Hello Word</div>
                            </Box>

                        </Stack>
                    </Box>
                    <Stack>
                        <div>Hello Word</div>
                    </Stack>
                </Stack>
            </Container>

        </>
    )
}