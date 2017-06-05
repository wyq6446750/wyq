<#include "../common/page.ftl"/>
<div class="box-body no-padding">
    <table class="table table-bordered table-striped table-hover text-sm">
        <thead>
        <tr class="info">
            <th class="text-center" width="5%">id</th>
            <th class="text-center">englishName</th>
            <th class="text-center">chinaName</th>
            <th class="text-center">age</th>
        </tr>
        </thead>
        <tbody>
        <#if pager?? && pager.pagerData?? && pager.pagerData?size gt 0>
            <#list pager.pagerData as user>
            <tr>
                <td class="text-center">
                ${user.id!}
                </td>
                <td class="text-center">
                ${user.englishName!}
                </td>
                <td class="text-center">
                ${user.chinaName!}
                </td>
                <td class="text-center">
                ${user.age!}
                </td>

            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<@pagination formUrl="/user/search?ajax=true&page=%d" currentPage=(pager.page)!0 totalPage=(pager.totalPages)!0 totalCount=(pager.totalCount)! />