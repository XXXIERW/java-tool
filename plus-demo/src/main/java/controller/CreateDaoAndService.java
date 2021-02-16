package controller;

import cn.tool.core.utils.AutoGenerateCodeUtils;

/**
 * <p>
 *     构建mybatis-plus中 构建dao,mapper,service
 *     测试demo
 * </p>
 */
public class CreateDaoAndService {

	public static void main(String[] args) throws Exception {
		AutoGenerateCodeUtils.generate("cn.plus-demo", Class.class);
	}
}