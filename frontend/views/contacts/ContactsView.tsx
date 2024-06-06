
import {ContactService} from "Frontend/generated/endpoints"
import {useEffect,useState} from "react";
import {Grid} from "@hilla/react-components/Grid";

export default function ContactsView(){

    const [contacts,setContacts] = useState([]);

    useEffect(() => {
        //ContactService.findAllContacts().then(setContacts);
    }, []);

    return (
        <div className="p-m flex gap-m">
            <Grid items={contacts}>

            </Grid>
        </div>
    );
}