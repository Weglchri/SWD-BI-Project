package at.fhj.swd.dbanw;

import javax.persistence.*;
import java.sql.Date;
import javax.persistence.SequenceGenerator;

@Entity public class Project {
    @Id private Integer projectId;
        private Integer capital;
        private Date creationDate;
        private String task;

    public Project(int projectId, int capital, Date creationDate, String task) {
        setProjectId(projectId);
        setCapital(capital);
        setCreationDate(creationDate);
        setTask(task);
    }

    public Project() {
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

}
