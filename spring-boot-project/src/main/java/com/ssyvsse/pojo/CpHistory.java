package com.ssyvsse.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author llb
 *
 * @Date 2017年12月11日
 */
public class CpHistory  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String no;

    private String before_nums;

    private String before_specialnums;

    private String after_nums;

    private String after_specialnums;

    private int typeid;

	private BigDecimal money;

    private String createtime;

    private String opendate;

    private String status;

    private String img;

    private Integer userid;

    private Integer auditUserid;

    private String comment;
    
    private String shortpy;
    
    private String audittime;
    
    private String platform;
    
    private String typename;
    
   
    
  

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		if(audittime.indexOf(".")>0){
    		int i = audittime.indexOf(".");
    		this.audittime = audittime.substring(0, i);
    	}else
    		this.audittime = audittime;
	}
    
	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getBefore_nums() {
		return before_nums;
	}

	public void setBefore_nums(String before_nums) {
		this.before_nums = before_nums == null ? null : before_nums.trim();
	}

	public String getBefore_specialnums() {
		return before_specialnums;
	}

	public void setBefore_specialnums(String before_specialnums) {
		this.before_specialnums = before_specialnums == null ? null : before_specialnums.trim();
	}

	public String getAfter_nums() {
		return after_nums;
	}

	public void setAfter_nums(String after_nums) {
		this.after_nums =  after_nums == null ? null : after_nums.trim();
	}

	public String getAfter_specialnums() {
		return after_specialnums;
	}

	public void setAfter_specialnums(String after_specialnums) {
		this.after_specialnums = after_specialnums == null ? null : after_specialnums.trim();
	}
    
    public String getShortpy() {
		return shortpy;
	}

	public void setShortpy(String shortpy) {
		this.shortpy = shortpy;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }


    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
    	
    	if(createtime.indexOf(".")>0){
    		int i = createtime.indexOf(".");
    		this.createtime = createtime.substring(0, i);
    	}else
    		this.createtime = createtime;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAuditUserid() {
        return auditUserid;
    }

    public void setAuditUserid(Integer auditUserid) {
        this.auditUserid = auditUserid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

	@Override
	public String toString() {
		return "CpHistory [id=" + id + ", no=" + no + ", before_nums=" + before_nums + ", before_specialnums="
				+ before_specialnums + ", after_nums=" + after_nums + ", after_specialnums=" + after_specialnums
				+ ", typeid=" + typeid + ", money=" + money + ", createtime=" + createtime + ", opendate=" + opendate
				+ ", status=" + status + ", img=" + img + ", userid=" + userid + ", auditUserid=" + auditUserid
				+ ", comment=" + comment + ", shortpy=" + shortpy + ", audittime=" + audittime + ", platform="
				+ platform + ", typename=" + typename + "]";
	}
}
