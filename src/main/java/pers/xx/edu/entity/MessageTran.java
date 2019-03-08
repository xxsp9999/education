package pers.xx.edu.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "message")
public class MessageTran
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//id

    private String btPacketType;     //数据包头，默认为0xA0
    private String btDataLen;        //数据包长度，数据包从‘长度’字节后面开始的字节数，不包含‘长度’字节本身
    private String btReadId;         //读写器地址
    private String btCmd;            //数据包命令代码
    private String btAryData;      //数据包命令参数，部分命令无参数
    private String btCheck;          //校验和，除校验和本身外所有字节的校验和

    private String btAryTranData;  //完整数据包


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date();//创建时间

    private String orderNumber;//单子编号

    private String state = "0";//状态

    private String type = ""; //类型 in;out

    //设置属性

    public MessageTran(){
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBtPacketType() {
		return btPacketType;
	}

	public void setBtPacketType(String btPacketType) {
		this.btPacketType = btPacketType;
	}

	public String getBtDataLen() {
		return btDataLen;
	}

	public void setBtDataLen(String btDataLen) {
		this.btDataLen = btDataLen;
	}

	public String getBtReadId() {
		return btReadId;
	}

	public void setBtReadId(String btReadId) {
		this.btReadId = btReadId;
	}

	public String getBtCmd() {
		return btCmd;
	}

	public void setBtCmd(String btCmd) {
		this.btCmd = btCmd;
	}

	public String getBtAryData() {
		return btAryData;
	}

	public void setBtAryData(String btAryData) {
		this.btAryData = btAryData;
	}

	public String getBtCheck() {
		return btCheck;
	}

	public void setBtCheck(String btCheck) {
		this.btCheck = btCheck;
	}

	public String getBtAryTranData() {
		return btAryTranData;
	}

	public void setBtAryTranData(String btAryTranData) {
		this.btAryTranData = btAryTranData;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "MessageTran [id=" + id + ", btPacketType=" + btPacketType
				+ ", btDataLen=" + btDataLen + ", btReadId=" + btReadId
				+ ", btCmd=" + btCmd + ", btAryData=" + btAryData
				+ ", btCheck=" + btCheck + ", btAryTranData=" + btAryTranData
				+ ", createDate=" + createDate + ", orderNumber=" + orderNumber
				+ ", state=" + state + ", type=" + type + "]";
	}



}