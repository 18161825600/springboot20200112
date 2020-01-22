package com.example.springboot20200112.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenic_spot_id")
    private Long scenicSpotId;

    @Column(name = "ticket_name")
    private String ticketName;

    @Column(name = "ticket_price_adult")
    private Double ticketPriceAdult;

    @Column(name = "ticket_price_student")
    private Double ticketPriceStudent;

    @Column(name = "ticket_number_adult")
    private Integer ticketNumberAdult;

    @Column(name = "ticket_number_student")
    private Integer ticketNumberStudent;

    @Column(name = "ticket_describe")
    private String ticketDescribe;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return scenic_spot_id
     */
    public Long getScenicSpotId() {
        return scenicSpotId;
    }

    /**
     * @param scenicSpotId
     */
    public void setScenicSpotId(Long scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    /**
     * @return ticket_name
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * @param ticketName
     */
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    /**
     * @return ticket_price_adult
     */
    public Double getTicketPriceAdult() {
        return ticketPriceAdult;
    }

    /**
     * @param ticketPriceAdult
     */
    public void setTicketPriceAdult(Double ticketPriceAdult) {
        this.ticketPriceAdult = ticketPriceAdult;
    }

    /**
     * @return ticket_price_student
     */
    public Double getTicketPriceStudent() {
        return ticketPriceStudent;
    }

    /**
     * @param ticketPriceStudent
     */
    public void setTicketPriceStudent(Double ticketPriceStudent) {
        this.ticketPriceStudent = ticketPriceStudent;
    }

    /**
     * @return ticket_number_adult
     */
    public Integer getTicketNumberAdult() {
        return ticketNumberAdult;
    }

    /**
     * @param ticketNumberAdult
     */
    public void setTicketNumberAdult(Integer ticketNumberAdult) {
        this.ticketNumberAdult = ticketNumberAdult;
    }

    /**
     * @return ticket_number_student
     */
    public Integer getTicketNumberStudent() {
        return ticketNumberStudent;
    }

    /**
     * @param ticketNumberStudent
     */
    public void setTicketNumberStudent(Integer ticketNumberStudent) {
        this.ticketNumberStudent = ticketNumberStudent;
    }

    /**
     * @return ticket_describe
     */
    public String getTicketDescribe() {
        return ticketDescribe;
    }

    /**
     * @param ticketDescribe
     */
    public void setTicketDescribe(String ticketDescribe) {
        this.ticketDescribe = ticketDescribe == null ? null : ticketDescribe.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}