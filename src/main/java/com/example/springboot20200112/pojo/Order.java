package com.example.springboot20200112.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "buy_adult_ticket_num")
    private Integer buyAdultTicketNum;

    @Column(name = "buy_student_ticket_num")
    private Integer buyStudentTicketNum;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_state")
    /**
     * 0是已退款
     * 1是交易成功
     */
    private Short orderState;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return ticket_id
     */
    public Long getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId
     */
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * @return buy_adult_ticket_num
     */
    public Integer getBuyAdultTicketNum() {
        return buyAdultTicketNum;
    }

    /**
     * @param buyAdultTicketNum
     */
    public void setBuyAdultTicketNum(Integer buyAdultTicketNum) {
        this.buyAdultTicketNum = buyAdultTicketNum;
    }

    /**
     * @return buy_student_ticket_num
     */
    public Integer getBuyStudentTicketNum() {
        return buyStudentTicketNum;
    }

    /**
     * @param buyStudentTicketNum
     */
    public void setBuyStudentTicketNum(Integer buyStudentTicketNum) {
        this.buyStudentTicketNum = buyStudentTicketNum;
    }

    /**
     * @return total_price
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return order_state
     */
    public Short getOrderState() {
        return orderState;
    }

    /**
     * @param orderState
     */
    public void setOrderState(Short orderState) {
        this.orderState = orderState;
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