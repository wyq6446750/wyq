<#include "../common/page.ftl"/>
<div class="box-body no-padding">
    <table class="table table-bordered table-striped table-hover text-sm">
        <thead>
        <tr class="info">
            <th class="text-center" width="5%">id</th>
            <th class="text-center">name</th>
            <th class="text-center">age</th>
        </tr>
        </thead>
        <tbody>
        <#if pager?? && pager.pagerData?? && pager.pagerData?size gt 0>
            <#list pager.pagerData as person>
            <tr>
                <td class="text-center">
                ${person.id!}
                </td>
                <td class="text-center">
                ${person.name!}
                </td>
                <td class="text-center">
                ${person.age!}
                </td>

            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<@pagination formUrl="/test/search?ajax=true&page=%d" currentPage=(pager.page)!0 totalPage=(pager.totalPages)!0 totalCount=(pager.totalCount)! />