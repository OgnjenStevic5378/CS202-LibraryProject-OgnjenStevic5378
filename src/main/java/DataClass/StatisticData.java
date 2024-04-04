package DataClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class StatisticData {
    private SimpleIntegerProperty idClientProperty;
    private SimpleStringProperty nameClientProperty;
    private SimpleIntegerProperty idCDProperty;
    private SimpleStringProperty nameCDProperty;
    private SimpleStringProperty cdAndClientBeginDateProperty;
    private SimpleStringProperty cdAndClientEndDateProperty;
    private Date cdAndClientBeginDate;
    private Date cdAndClientEndDate;

    public StatisticData() {
    }

    public StatisticData(int idClient, String nameClient, int idCD, String nameCD, Date cdAndClientBeginDate, Date cdAndClientEndDate) {
        this.idClientProperty = new SimpleIntegerProperty(idClient);
        this.nameClientProperty = new SimpleStringProperty(nameClient);
        this.idCDProperty = new SimpleIntegerProperty(idCD);
        this.nameCDProperty = new SimpleStringProperty(nameCD);
        this.cdAndClientBeginDate = cdAndClientBeginDate;
        this.cdAndClientEndDate = cdAndClientEndDate;

        this.cdAndClientBeginDateProperty = new SimpleStringProperty(cdAndClientBeginDate.toString());

        try {
            this.cdAndClientEndDateProperty = new SimpleStringProperty(cdAndClientEndDate.toString());
        } catch (Exception e) {
            this.cdAndClientEndDateProperty = null;
        }
    }

    public static void StatisticStage() {
    }

    public int getIdClientProperty() {
        return idClientProperty.get();
    }

    public SimpleIntegerProperty idClientPropertyProperty() {
        return idClientProperty;
    }

    public void setIdClientProperty(int idClientProperty) {
        this.idClientProperty.set(idClientProperty);
    }

    public String getNameClientProperty() {
        return nameClientProperty.get();
    }

    public SimpleStringProperty nameClientPropertyProperty() {
        return nameClientProperty;
    }

    public void setNameClientProperty(String nameClientProperty) {
        this.nameClientProperty.set(nameClientProperty);
    }

    public int getIdCDProperty() {
        return idCDProperty.get();
    }

    public SimpleIntegerProperty idCDPropertyProperty() {
        return idCDProperty;
    }

    public void setIdCDProperty(int idCDProperty) {
        this.idCDProperty.set(idCDProperty);
    }

    public String getNameCDProperty() {
        return nameCDProperty.get();
    }

    public SimpleStringProperty nameCDPropertyProperty() {
        return nameCDProperty;
    }

    public void setNameCDProperty(String nameCDProperty) {
        this.nameCDProperty.set(nameCDProperty);
    }

    public String getCdAndClientBeginDateProperty() {
        return cdAndClientBeginDateProperty.get();
    }

    public SimpleStringProperty cdAndClientBeginDatePropertyProperty() {
        return cdAndClientBeginDateProperty;
    }

    public void setCdAndClientBeginDateProperty(String cdAndClientBeginDateProperty) {
        this.cdAndClientBeginDateProperty.set(cdAndClientBeginDateProperty);
    }

    public String getCdAndClientEndDateProperty() {
        return cdAndClientEndDateProperty.get();
    }

    public SimpleStringProperty cdAndClientEndDatePropertyProperty() {
        return cdAndClientEndDateProperty;
    }

    public void setCdAndClientEndDateProperty(String cdAndClientEndDateProperty) {
        this.cdAndClientEndDateProperty.set(cdAndClientEndDateProperty);
    }

    public Date getCdAndClientBeginDate() {
        return cdAndClientBeginDate;
    }

    public StatisticData setCdAndClientBeginDate(Date cdAndClientBeginDate) {
        this.cdAndClientBeginDate = cdAndClientBeginDate;
        return this;
    }

    public Date getCdAndClientEndDate() {
        return cdAndClientEndDate;
    }

    public StatisticData setCdAndClientEndDate(Date cdAndClientEndDate) {
        this.cdAndClientEndDate = cdAndClientEndDate;
        return this;
    }

    @Override
    public String toString() {
        return "StatisticData{" +
                "idClientProperty=" + idClientProperty +
                ", nameClientProperty=" + nameClientProperty +
                ", idCDProperty=" + idCDProperty +
                ", nameCDProperty=" + nameCDProperty +
                ", cdAndClientBeginDateProperty=" + cdAndClientBeginDateProperty +
                ", cdAndClientEndDateProperty=" + cdAndClientEndDateProperty +
                '}';
    }
}
