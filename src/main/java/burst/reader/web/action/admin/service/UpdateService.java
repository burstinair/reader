package burst.reader.web.action.admin.service;

import burst.reader.web.action.admin.model.UpdateActionModel;
import burst.reader.web.action.service.BaseService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-17
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public class UpdateService extends BaseService {

    public String doExecute(UpdateActionModel updateActionModel) throws Exception {

        if("get_history".equals(updateActionModel.getAction())) {

        } else if("get_last_append".equals(updateActionModel.getAction())) {

        } else if("get_append_between".equals(updateActionModel.getAction())) {

        } else if("append".equals(updateActionModel.getAction())) {

        } else if("append_from_version".equals(updateActionModel.getAction())) {

        }

        return ActionSupport.SUCCESS;
    }
}
