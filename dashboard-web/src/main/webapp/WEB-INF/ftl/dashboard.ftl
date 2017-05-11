<#include "common/html.ftl"/>
<@html title="网页设计" pageNo="1000">
<script type="text/javascript">
    $(document).ready(function(){
        var lis = $('.treeview-menu li');
        var home = $('.treeview-menu li:first').find('a').attr('href');
        $.each(lis, function () {
            var href = $(this).find('a').attr('href');
            if(href == '/test/search'){
                home = href;
                return;
            }
        });
        window.self.location = home;
    });
</script>
</@html>