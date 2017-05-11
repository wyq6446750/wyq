<#include "../common/html.ftl"/>
<#include "../common/page.ftl"/>
<@html title="Test" pageNo="1001">
<div class="row">
    <div class="col-md-12">
        <div class="box box-primary" style="margin-bottom:10px;">
            <form action="/test/search" id="coupon-search-form" method="post" autocomplete="off">
                <div class="box-body">
                    <table>
                        <tr>
                            <td class="text-right" width="6%">
                                <span style="padding-right:5px;">id:</span>
                            </td>
                            <td width="9%">
                                <input type="text" class="form-control" name="id">
                            </td>
                            <td class="text-right" width="6%">
                                <span style="padding-right:5px;">name</span>
                            </td>
                            <td width="9%">
                                <input type="text" class="form-control" name="name">
                            </td>
                            <td class="text-right" width="6%">
                                <span style="padding-right:5px;">age</span>
                            </td>
                            <td width="9%">
                                <input type="text" class="form-control" name="age">
                            </td>
                            <td class="text-right">
                                <button type="submit" class="btn btn-primary btn-flat"><i class="fa fa-search"></i> 查 询 </button>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div id="coupon-list-datas" class="box no-margin">
            <#include "coupon-list.ftl" />
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).delegate("#coupon-search-form", "submit", function(e) {
        e.preventDefault;
        searchList('/test/search?ajax=true');
        return false;
    });

    $(document).delegate(".pagination li a", "click", function(e) {
        e.preventDefault;
        searchList($(this).attr("href"));
        return false;
    });

    function searchList(url) {
        $.isLoading({text:"数据查询中,请稍后!"});
        $.ajax({
            type: 'post',
            url: url,
            data: $("#coupon-search-form").serialize(),
            success: function(data) {
                $('#coupon-list-datas').html(data);
                $.isLoading("hide");
            }
        });
    }
</script>
</@html>