package model;

import java.util.Objects;

public class ComputerComponents {

    private String cpu;
    private String motherboard;
    private String computerCase;
    private String gpu;
    private String ram;

    public ComputerComponents(String cpu, String motherboard,String computerCase,String gpu,String ram) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.computerCase = computerCase;
        this.gpu = gpu;
        this.ram = ram;
    }

    public String getCpuName() {
        return cpu;
    }
    public void setCpuName(String cpu) {
        this.cpu = cpu;
    }

    public String getMotherboardName() {
        return motherboard;
    }
    public void setMotherboardName(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getComputerCaseName() {
        return computerCase;
    }
    public void setComputerCaseName(String computerCase) {
        this.computerCase = computerCase;
    }

    public String getGpuName() {
        return gpu;
    }
    public void setGpuName(String gpu) {
        this.gpu = gpu;
    }

    public String getRamName() {
        return ram;
    }
    public void setRamName(String ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "ComputerComponents{" +
                "CPU='" + cpu + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", computerCase='" + computerCase + '\'' +
                ", GPU='" + gpu + '\'' +
                ", RAM='" + ram + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComputerComponents)) return false;
        ComputerComponents computerComponents = (ComputerComponents) o;
        return Objects.equals(getCpuName(), computerComponents.getCpuName()) &&
                Objects.equals(getMotherboardName(), computerComponents.getMotherboardName())&&
                Objects.equals(getComputerCaseName(), computerComponents.getComputerCaseName())&&
                Objects.equals(getGpuName(), computerComponents.getGpuName())&&
                Objects.equals(getRamName(), computerComponents.getRamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpuName(), getMotherboardName(),getComputerCaseName(),getGpuName(),getRamName());
    }
}
