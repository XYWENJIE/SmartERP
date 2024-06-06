import {useEffect,useState} from "react";
import {PersonnelService} from "Frontend/generated/endpoints";
import PersonnelRecord from "Frontend/generated/com/benjamin/smarterp/service/PersonnelService/PersonnelRecord";
import {Grid} from "@hilla/react-components/Grid";
import {GridColumn} from "@hilla/react-components/GridColumn";
import {Button} from "@hilla/react-components/Button";

export default function PersonnelView() {
    const [personnel,setPersonnel] = useState<PersonnelRecord[]>([]);

    useEffect(()=>{
        // @ts-ignore
        PersonnelService.findPersonnelAll().then(setPersonnel);
    },[]);



    return (
        <div>
            <Button>添加</Button>
            <Grid items={personnel}>
                <GridColumn path="email"/>
            </Grid>
        </div>
    );
}