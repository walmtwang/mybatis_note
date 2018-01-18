package cn.walmt.mapper;

import cn.walmt.pojo.Orders;
import cn.walmt.pojo.OrdersCustom;
import cn.walmt.pojo.User;
import org.apache.tools.ant.taskdefs.condition.Or;

import java.util.List;

/**
 * Created by walmt on 2018/1/15.
 */
public interface OrdersMapperCustom {

    // 查询订单关联查询用户信息
    List<OrdersCustom> findOrdersUser() throws Exception;

    // 查询订单关联用户使用resultMap
    List<Orders> findOrdersUserResultMap() throws Exception;

    // 查询订单（关联用户）及订单明细
    List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

    // 查询用户购买商品信息
    List<User> findUserAndItemsResultMap() throws Exception;

    // 查询订单关联查询用户
    List<Orders> findOrdersUserLazyLoading() throws Exception;
}
