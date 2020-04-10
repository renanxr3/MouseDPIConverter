package br.com.renaninfo.mousedpiconverter.components.itemnewconfiguration;

public class NewConfigurationItemModel {

    String dpi;
    String sensitivity;

    public NewConfigurationItemModel(String dpi, String sensitivity) {
        this.dpi = dpi;
        this.sensitivity = sensitivity;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

}
