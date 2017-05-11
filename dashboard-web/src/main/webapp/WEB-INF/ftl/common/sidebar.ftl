<#include "menu.ftl"/>
<#macro sidebar pageNo="1000">
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu">
            <#list menu_main_bar?keys as bar>
                <#assign pageIndex = bar?split("-")[1]/>
                <li class="<#if pageNo?substring(0,1)==pageIndex>active </#if>treeview">
                    <a href="/">
                        <#assign bar_info = menu_main_bar[bar]?split(",")/>
                        <i class="fa fa-${bar_info[1]}<#if pageNo?substring(0,1)==pageIndex> text-info</#if>"></i>
                        <span>${bar_info[0]}</span>
                        <span class="pull-right-container">
                      <i class="fa fa-angle-right pull-right"></i>
                    </span>
                    </a>
                    <#assign sub_menu = menu_sub_bar[pageIndex]/>
                    <ul class="treeview-menu">
                        <#list sub_menu?keys as page>
                            <#assign page_info = sub_menu[page]?split(",")/>
                            <#assign isActive = pageNo==page/>
                            <li <#if isActive>class="active" style="background-color:#d2d6de;" </#if>>
                                <a href="${page_info[1]}">
                                    <i class="fa fa-arrow-circle-o-right<#if isActive> text-danger</#if>"></i>
                                ${page_info[0]}
                                </a>
                            </li>
                        </#list>
                    </ul>
                </li>
            </#list>
        </ul>
    </section>
</aside>
</#macro>