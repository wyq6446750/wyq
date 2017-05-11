<#include "head.ftl"/>
<#include "sidebar.ftl"/>
<#macro html title='网页设计' pageNo='1000'>
<!DOCTYPE html>
<html lang="zh-CN">
<@head title = title/>
<body class="hold-transition skin-blue-light sidebar-mini fixed">
<div class="wrapper" style="position:static;">
    <#include "header.ftl"/>
    <@sidebar pageNo = pageNo/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <i class="fa fa-home"></i> <small style="font-weight: bold"> ${title!}</small>
            </h1>
        </section>
        <section class="content">
            <#nested />
        </section>
    </div>
    <#include "footer.ftl"/>
</div>
</body>
</html>
</#macro>