
package com.shu.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 下级单位表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cascade_sub_node", uniqueConstraints = {
		@UniqueConstraint(name = "cascade_sub_node_sub_ip_unique", columnNames = { "sub_ip"}),
		@UniqueConstraint(name = "cascade_sub_node_name_unique", columnNames = { "name"})})
public class CascadeSubNode implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "cascade_code", length = 32)
	private String cascadeCode;

	@Column(name = "name", length = 128)
	private String name;

	@Column(name = "area", length = 128)
	private String area;

	//1 未注册 2 已注册 3 注册失败 4 已注销  5 注销失败
	@Column(name = "register_status")
	private Integer registerStatus;

	@Column(name = "register_msg", length = 512)
	private String registerMsg;

	@Column(name = "connect_status")
	private Integer connectStatus;

	@Column(name = "local_ip", length = 128)
	private String localIp;

	@Column(name = "sub_ip", length = 128)
	private String subIp;

	@Column(name = "local_port")
	private Integer localPort;

	@Column(name = "sub_port")
	private Integer subPort;

	@Column(name = "secret_key", length = 32)
	private String secretKey;

	@Column(name = "token", length = 256)
	private String token; // 网关token

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;
}
