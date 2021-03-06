package info.xiancloud.core.test.output_test;

import info.xiancloud.core.message.UnitRequest;
import info.xiancloud.core.message.UnitResponse;
import info.xiancloud.core.Group;
import info.xiancloud.core.Input;
import info.xiancloud.core.message.UnitResponse;
import info.xiancloud.core.Unit;
import info.xiancloud.core.message.UnitRequest;
import info.xiancloud.core.test.TestGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author happyyangyuan
 */
public class UnitResponseTestUnit implements Unit {

    @Override
    public String getName() {
        return "responseTestUnit";
    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public UnitResponse execute(UnitRequest msg) {
        Map<String, String> map = new HashMap<>();
        map.put("这是测试key0", "这是测试值0");
        map.put("这是测试key1", "这是测试值1");
        return UnitResponse.success(map);
    }

    public String getThisGetterShouldNotSerialize() {
        throw new RuntimeException("序列化unit时这个getter方法不应该被调用，否则就是bug");
    }

    @Override
    public Group getGroup() {
        return TestGroup.singleton;
    }
}
