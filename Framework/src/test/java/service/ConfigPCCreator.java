package service;

import model.ComputerComponents;

public class ConfigPCCreator {

    public static final String CPU_NAME = "Процессор AMD Athlon X4 840 OEM";
    public static final String MOTHERBOARD_NAME = "Материнская плата Biostar A68MHE";
    public static final String COMPUTER_CASE_NAME = "Корпус GiNZZU D180 [17223] черный";
    public static final String GPU_NAME = "Видеокарта MSI GeForce GT 710 Silent LP [GT 710 1GD3H LP]";
    public static final String RAM_NAME = "Оперативная память AMD Radeon R5 Entertainment Series [R532G1601U1S-U] 2 ГБ";

    public static ComputerComponents withCredentialsFromProperty(){
        return new ComputerComponents(CPU_NAME,
                MOTHERBOARD_NAME,
                COMPUTER_CASE_NAME,
                GPU_NAME,
                RAM_NAME);
    }

    public static ComputerComponents allEmptyBesidesCPU(){
        return new ComputerComponents(CPU_NAME,"","","","");
    }
    public static ComputerComponents allEmptyBesidesMotherboard(){
        return new ComputerComponents("",MOTHERBOARD_NAME, "","","");
    }
    public static ComputerComponents allEmptyBesidesComputerCase(){
        return new ComputerComponents("","",COMPUTER_CASE_NAME,"","");
    }
    public static ComputerComponents allEmptyBesidesGPU(){
        return new ComputerComponents("","","",GPU_NAME,"");
    }
    public static ComputerComponents allEmptyBesidesRAM(){
        return new ComputerComponents("","","","",RAM_NAME);
    }
}
