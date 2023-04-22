package com.oa.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * user table
 * </p>
 *
 * @author SYJ
 * @since 2023-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * name
     */
    private String name;

    /**
     * phone number
     */
    private String phone;

    /**
     * url of head image
     */
    private String headUrl;

    /**
     * department id
     */
    private Long deptId;

    /**
     * post id
     */
    private Long postId;

    /**
     * wechat open id
     */
    private String openId;

    /**
     * description
     */
    private String description;

    /**
     * status: 1 for normal, 0 for forbidden
     */
    private Integer status;

    /**
     * time of creation
     */
    private LocalDateTime createTime;

    /**
     * time of update
     */
    private LocalDateTime updateTime;

    /**
     * delete flag (0: not deleted, 1: deleted)
     */
    private Integer isDeleted;


}
