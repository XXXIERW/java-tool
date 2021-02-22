package cn.tool.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式实现, 可以这样调用:<br/>
 * <code>MqClient mqClient = Singleton.getInstance(MqClient.class);</code><br/>
 * MqClient必须实现一个没有参数的构造函数: <br/>
 * <code><pre>
 * public MqClient(){
 *     .....
 * }</pre></code> 对于已经存在的实例, 可以这样做:<br/>
 * <code><pre>
 * <code>@PostConstruct</code>
 * void init(...){
 *     Singleton.setInstance(this);
 * }
 * </pre></code> 对于带参数的, 可以这样做:<br/>
 * <code><pre>
 * public NetModule(String ip, int port){...}
 * 
 * NetModule netModule = new NetModule("123.456.7.8", 90);
 * Singleton.setInstance(netModule);
 * </pre></code>
 * 
 * @since 1.0
 */
public class Singleton {
	
	private static final Logger logger = LoggerFactory.getLogger(Singleton.class);

	public interface InstanceFactory<T> {
		T createInstance();
	}

	private static Map<Object, Object> all = new ConcurrentHashMap<>(32);

	/**
	 * 单例的简单变种实现 给一个类型, 返回一个实例, 如果这个实例以前没有创建过, 则会使用默认的构造函数创建一个实例.
	 */
	public static <T> T getInstance(Class<T> cls) {
		return getInstance(cls, cls, null);
	}
	
	/**
	 * 单例，需要提供一个key, 以这个key获取的会是同一个实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> cls, String key){
		Object obj = all.get(cls.getName() + "|" + key);
		if (obj == null) {
			synchronized (cls) {
				obj = all.get(cls.getName() + "|" + key);
				if (obj == null) {
					try {
						obj = cls.newInstance();
						all.put(cls.getName() + "|" + key, obj);
					} catch (Exception e) {
						logger.error("Get instance fail, cause: {}", e);
					}
				}
			}
		}
		return (T) obj;
	}
	
	/**
	 * 工厂支持
	 *
	 * @param superCls
	 *            父类/抽象类或接口
	 * @param objCls
	 *            子类或实现类
	 * @return 返回父类/抽象类或接口
	 */
	public static <T> T getInstance(Class<T> superCls, Class<? extends T> objCls) {
		return getInstance(superCls, objCls, null);
	}
	public static <T> T getInstance(Class<T> objCls, InstanceFactory<T> instanceFactory) {
		return getInstance(objCls, objCls, instanceFactory);
	}

	/**
	 * 支持工厂的单例
	 * 
	 * @param <T>
	 * @param superCls
	 *            父类/抽象类或接口
	 * @param objCls
	 *            子类或实现类
	 * @param instanceFactory
	 *            创建实例时的回调
	 * @return 返回父类/抽象类或接口
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> superCls, Class<? extends T> objCls, InstanceFactory<T> instanceFactory) {
		Object obj = all.get(superCls);
		if (obj == null) {
			synchronized (superCls) {
				obj = all.get(superCls);
				if (obj == null) {
					try {
						if (instanceFactory != null) {
							obj = instanceFactory.createInstance();
						} else {
							obj = objCls.newInstance();
						}
						all.put(superCls, obj);
					} catch (Exception e) {
						logger.error("Get instance fail, cause: {}", e);
					}
				}
			}
		}
		return (T) obj;
	}

	/**
	 * 设置一个实例, 下次可以通过getInstance来获取
	 */
	public synchronized static <T> void setInstance(T obj, String key) {
		//assert (obj != null);
		if (obj != null && key != null && !"".equals(key.trim())) {
			all.put(obj.getClass() + "|" + key, obj);
		}
	}

	/**
	 * 设置一个实例, 下次可以通过getInstance来获取
	 */
	public synchronized static <T> void setInstance(T obj) {
		//assert (obj != null);
		if (obj != null) {
			all.put(obj.getClass(), obj);
		}
	}

	public synchronized static <T> void removeInstance(Class<T> cls) {
		all.remove(cls);
	}

	/**
	 * 工厂模式支持
	 *
	 * @param superCls
	 *            接口类型/抽象类/父类
	 * @param obj
	 *            实例/子类
	 */
	public synchronized static <T> void setInstance(Class<? super T> superCls, T obj) {
		//assert (obj != null && superCls != null);
		if (superCls != null && obj != null) {
			all.put(superCls, obj);
		}
	}

}
