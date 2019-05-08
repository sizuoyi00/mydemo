package cn.sy.demo.constant;

public class MQConstant {

	/**
	 * Exchange
	 * direct,fanout,topic,headers 四种交换器
	 * direct:路由键完全匹配时，消息才投放到对应队列。AMQP实现都必须有一个direct交换器（默认交换器），
	 *        名称为空白字符。队列不声明交换器，会自动绑定到默认交换器，队列的名称作为路由键
	 * fanout:可以理解为广播，会把交换器上的所有消息投放到绑定到这个交换机上的队列上，无论这个队列是通过哪个路由键绑定到这个交换器上的
	 * topic:主题，使来自不同源头的消息到达同一个队列
	 * headers:匹配消息头，其余与direct一样，实用性不大
	 */
	public final static String EXCHANGE_TEST = "DEMO_EXCHANGE_TEST";

	/**
	 * Bind-key
	 */
	public final static String KEY_TEST = "DEMO_TEST_KEY";

	/**
	 * Queue
	 */
	public final static String QUEUE_TEST = "DEMO_TEST_QUEUE";

}
