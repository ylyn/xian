package info.xiancloud.cache.service.unit.list;

import info.xiancloud.cache.redis.Redis;
import info.xiancloud.cache.redis.util.FormatUtil;
import info.xiancloud.cache.service.CacheGroup;
import info.xiancloud.core.Group;
import info.xiancloud.core.Input;
import info.xiancloud.core.Unit;
import info.xiancloud.core.UnitMeta;
import info.xiancloud.core.message.UnitRequest;
import info.xiancloud.core.message.UnitResponse;
import info.xiancloud.core.support.cache.CacheConfigBean;

/**
 * List Add Head
 *
 * @author John_zero
 */
public class CacheListAddHeadUnit implements Unit {
    @Override
    public String getName() {
        return "cacheListAddHead";
    }

    @Override
    public Group getGroup() {
        return CacheGroup.singleton;
    }

    @Override
    public UnitMeta getMeta() {
        return UnitMeta.create("List Add Head").setPublic(false);
    }

    @Override
    public Input getInput() {
        return new Input()
                .add("key", Object.class, "缓存的关键字", REQUIRED)
                .add("valueObj", Object.class, "", REQUIRED)
                .add("cacheConfig", CacheConfigBean.class, "", NOT_REQUIRED);
    }

    @Override
    public UnitResponse execute(UnitRequest msg) {
        String key = msg.getArgMap().get("key").toString();
        Object valueObj = msg.getArgMap().get("valueObj");
        CacheConfigBean cacheConfigBean = msg.get("cacheConfig", CacheConfigBean.class);

        try {
            long length = Redis.call(cacheConfigBean, (jedis) -> {
                String value = FormatUtil.formatValue(valueObj);
                return jedis.lpush(key, value);
            });
            return UnitResponse.success(length);
        } catch (Exception e) {
            return UnitResponse.exception(e);
        }
    }

}
