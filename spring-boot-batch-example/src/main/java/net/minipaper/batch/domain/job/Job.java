package net.minipaper.batch.domain.job;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "jobs", indexes = {
        @Index(name = "idx_job_name", columnList = "name")
})
class Job {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name", unique = true)
    @Size(max = 1000)
    private String name;

    @Column(name = "time_expression")
    private String timeExpression;


}
