package info.xiancloud.qcloudcos.unit;

import info.xiancloud.core.Group;
import info.xiancloud.core.Input;
import info.xiancloud.core.Unit;
import info.xiancloud.core.message.UnitRequest;
import info.xiancloud.core.message.UnitResponse;
import info.xiancloud.qcloudcos.sdk.CosFileWriter;

/**
 * @author happyyangyuan
 */
public class CosWriteUnit implements Unit {
    @Override
    public String getName() {
        return "cosWrite";
    }

    @Override
    public Group getGroup() {
        return new CosGroup();
    }

    @Override
    public Input getInput() {
        return new Input().add("path", String.class, "业务文件路径，相对路径")
                .add("data", String.class, "文件内容", REQUIRED);
    }

    @Override
    public UnitResponse execute(UnitRequest msg) {
        CosFileWriter writer = new CosFileWriter();
        if (writer.forPath(msg.get("path", String.class), msg.get("data", String.class))) {
            writer.close();
            return UnitResponse.success();
        } else {
            writer.close();
            return UnitResponse.failure(null, null);
        }
    }
}
