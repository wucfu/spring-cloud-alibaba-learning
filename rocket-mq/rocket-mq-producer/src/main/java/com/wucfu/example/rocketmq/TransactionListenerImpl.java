package com.wucfu.example.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(txProducerGroup = "myTxProducerGroup", corePoolSize = 5,
        maximumPoolSize = 10)
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    /**
     * 发送prepare消息成功此方法被回调，该方法用于执行本地事务
     * @param msg 回传的消息
     * @param arg 调用send方法时传递的参数，当send时候若有额外的参数可以传递到send方法中，这里能获取到
     * @return 返回事务状态，COMMIT：提交  ROLLBACK：回滚  UNKNOW：中间状态，需要检查消息队列来确定状态
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg,
                                                                 Object arg) {
        Object num = msg.getHeaders().get("test");

        if ("1".equals(num)) {
            System.out.println(
                    "executer: " + new String((byte[]) msg.getPayload()) + " unknown");
            return RocketMQLocalTransactionState.UNKNOWN;
        } else if ("2".equals(num)) {
            System.out.println(
                    "executer: " + new String((byte[]) msg.getPayload()) + " rollback");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        System.out.println(
                "executer: " + new String((byte[]) msg.getPayload()) + " commit");
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     *
     * @param msg 消息
     * @return 返回事务状态，COMMIT：提交  ROLLBACK：回滚  UNKNOW：中间状态，需要检查消息队列来确定状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("check: " + new String((byte[]) msg.getPayload()));
        return RocketMQLocalTransactionState.COMMIT;
    }

}
