import {Helmet} from "react-helmet-async";
import {ChatView} from "../sections/chat/view";

export default function ChatPage(){
    return (
        <>
            <Helmet>
                <title>Chat | Minimal UI</title>
            </Helmet>

            <ChatView/>
        </>
    )
}