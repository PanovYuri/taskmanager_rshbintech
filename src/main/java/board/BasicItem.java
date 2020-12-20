package board;

import java.time.LocalDate;

/**
* Данный класс является базовым для элементов доски
 * dateCreate        - дата создания
 * dateStart         - дата начала
 * dateComplete      - дата завершения
 * status            - текущий статс: NEW, ANALYSIS, WORK, COMPLETE
 * responsiblePerson - ответственное лицо
 * completePercent   - готовность проекта в процентах*/
abstract class BasicItem {

    private LocalDate dateCreate;
    private LocalDate dateStart;
    private LocalDate dateComplete;

    private Status status;

    private String responsiblePerson;

    private int completePercent;

    public BasicItem(LocalDate dateCreate, LocalDate dateStart, LocalDate dateComplete, Status status, String responsiblePerson, int completePercent) {
        try {
            setDateCreate(dateCreate);
            setDateStart(dateStart);
            setDateComplete(dateComplete);
            setStatus(status);
            setResponsiblePerson(responsiblePerson);
            setCompletePercent(completePercent);
        } catch (IncorrectParameter incorrectParameter) {
            incorrectParameter.printStackTrace();
        }
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public int getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(int completePercent) {
        this.completePercent = completePercent;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) throws IncorrectParameter {
        if (dateStart.compareTo(dateCreate) < 0)
            throw new IncorrectParameter("Дата начала меньше даты создания");
        this.dateStart = dateStart;
    }

    public LocalDate getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(LocalDate dateComplete) throws IncorrectParameter {
        if (dateComplete.compareTo(dateStart) < 0)
            throw new IncorrectParameter("Дата завершения меньше даты начала");
        this.dateComplete = dateComplete;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) throws IncorrectParameter {
        if (status != Status.NEW && this.dateStart == null)
            throw new IncorrectParameter("Не указана дата начала для статуса отличным от NEW");
        if (status == Status.COMPLETE && dateComplete == null)
            throw new IncorrectParameter("Не указана дата завершения");
        if (status == Status.COMPLETE && completePercent != 100)
            throw new IncorrectParameter("Готовность не равна 100");
        this.status = status;
    }
}
