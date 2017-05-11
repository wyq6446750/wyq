/**
 * Created by wei.feng on 16-8-16.
 */
$("#btnInsertRule").click(function() {
    $("#calculate-rule-edit-title").html("增加计算规则");
    reset();
    $("#modal-calculate-edit").modal("show");
})
$("#btnUpdateRule").click(function() {
    $("#calculate-rule-edit-title").html("修改计算规则");
    $("#modal-calculate-edit").modal("show");
})
//检查是否为整数
function isInteger(va)
{
    var reg=/^-?\d+$/;
    return reg.test(va);
}

//检查是否数字
function isNumber(va)
{
    var reg = /^-?\d+(.\d+)?$/;
    return reg.test(va);
}

$("#btnSave").click(function () {
    if(validate()) {
        save();
    }
});

function validate() {
    $("#ruleName").val($("#ruleName").val().trim());
    $("#sellFrom").val($("#sellFrom").val().trim());
    $("#sellTo").val($("#sellTo").val().trim());
    $("#reserveFrom").val($("#reserveFrom").val().trim());
    $("#reserveTo").val($("#reserveTo").val().trim());
    $("#rateFrom").val($("#rateFrom").val().trim());
    $("#rateTo").val($("#rateTo").val().trim());
    $("#commissionFrom").val($("#commissionFrom").val().trim());
    $("#commissionTo").val($("#commissionTo").val().trim());
    $("#base").val($("#base").val().trim());
    $("#multiple").val($("#multiple").val().trim());
    $("#addend").val($("#addend").val().trim());
    $("#upperLimit").val($("#upperLimit").val().trim());
    $("#lowerLimit").val($("#lowerLimit").val().trim());

    if($("#ruleName").val()=="") {
        alert("规则名称不能为空");
        return false;
    }
    if($("#sellFrom").val()!="" || $("#sellTo").val()!="") {
        if ($("#sellFrom").val()=="" || $("#sellTo").val()=="") {
            alert("卖价范围不完整");
            return false;
        }
        if (!isNumber($("#sellFrom").val()) || !isNumber($("#sellTo").val())) {
            alert("卖价范围只能是数字");
            return false;
        }
        if ($("#sellFrom").val() > $("#sellTo").val()) {
            alert("卖价范围设置大小值有误");
            return false;
        }
    }
    if($("#reserveFrom").val()!="" || $("#reserveTo").val()!="") {
        if ($("#reserveFrom").val()=="" || $("#reserveTo").val()=="") {
            alert("底价范围不完整");
            return false;
        }
        if (!isNumber($("#reserveFrom").val()) || !isNumber($("#reserveTo").val())) {
            alert("底价范围只能是数字");
            return false;
        }
        if ($("#reserveFrom").val() > $("#reserveTo").val()) {
            alert("底价范围设置大小值有误");
            return false;
        }
    }
    if($("#rateFrom").val()!="" || $("#rateTo").val()!="") {
        if ($("#rateFrom").val()=="" || $("#rateTo").val()=="") {
            alert("佣金比率范围不完整");
            return false;
        }
        if (!isNumber($("#rateFrom").val()) || !isNumber($("#rateTo").val())) {
            alert("佣金比率范围只能是数字");
            return false;
        }
        if ($("#rateFrom").val() > $("#rateTo").val()) {
            alert("佣金比率范围设置大小值有误");
            return false;
        }
    }
    if($("#commissionFrom").val()!="" || $("#commissionTo").val()!="") {
        if ($("#commissionFrom").val()=="" || $("#commissionTo").val()=="") {
            alert("佣金范围不完整");
            return false;
        }
        if (!isNumber($("#commissionFrom").val()) || !isNumber($("#commissionTo").val())) {
            alert("佣金范围只能是数字");
            return false;
        }
        if ($("#commissionFrom").val() > $("#commissionTo").val()) {
            alert("佣金范围设置大小值有误");
            return false;
        }
    }
    if($("#base").val()=="") {
        alert("设置值不能为空");
        return false;
    }
    if ($("#valueType").val() == 1) {
        if(!isNumber($("#base").val())) {
            alert("设置值只能是数字");
            return false;
        }
    }
    if ($("#valueType").val() == 0) {
        if(!isInteger($("#base").val())) {
            alert("设置值只能是整数");
            return false;
        }
    }
    if($("#multiple").val()=="") {
        alert("倍数不能为空");
        return false;
    }
    if(!isNumber($("#multiple").val())) {
        alert("倍数只能是数字");
        return false;
    }
    if($("#addend").val()=="") {
        alert("加数不能为空");
        return false;
    }
    if(!isNumber($("#addend").val())) {
        alert("加数只能是数字");
        return false;
    }
    if($("#upperLimit").val()=="") {
        alert("结果上限值不能为空");
        return false;
    }
    if(!isNumber($("#upperLimit").val())) {
        alert("结果上限值只能是数字");
        return false;
    }
    if($("#lowerLimit").val()=="") {
        alert("结果下限值不能为空");
        return false;
    }
    if(!isNumber($("#lowerLimit").val())) {
        alert("结果下限值只能是数字");
        return false;
    }
    return true;
}
function save() {
    $.ajax({
        type: "POST",
        url: "/calculate/save",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {couponCalculateRuleId:$("#ruleId").val(), couponCalculateRuleName:$("#ruleName").val(),
            getValueType:$("#type").val(), ruleDescription:$("#description").val(),
            sellPriceRangeFrom:$("#sellFrom").val(), sellPriceRangeTo:$("#sellTo").val(),
            reservePriceRangeFrom:$("#reserveFrom").val(), reservePriceRangeTo:$("#reserveTo").val(),
            commissionRateRangeFrom:$("#rateFrom").val(), commissionRateRangeTo:$("#rateTo").val(),
            commissionRangeFrom:$("#commissionFrom").val(), commissionRangeTo:$("#commissionTo").val(),
            setValueType:$("#baseType").val(), setValueStyle:$("#valueType").val(),setValue:$("#base").val(),
            calculateType:$("#resultType").val(), integerMultiples:$("#multiple").val(), addend:$("#addend").val(),
            upperLimit:$("#upperLimit").val(), lowerLimit:$("#lowerLimit").val()},
        dataType: "json",
        success: function(result){
            if (result.code == "SUCCESS"){
                $("#modal-calculate-edit").modal("hide");
                location.reload();
            } else {
                alert(result.msg);
            }
        },
        error: function(){
            alert("error message");
        }

    });
}
function optUpdate(ruleId) {
    $("#calculate-rule-edit-title").html("修改计算规则");
    $("#type").attr("disabled", "disabled");
//    $("#ruleName").attr("disabled", "disabled");
    $("#modal-calculate-edit").modal("show");

    $.ajax({
        type:"GET",
        url: "/calculate/getById?id="+ ruleId,
        dataType: "json",
        success: function(rule){
            initialize(rule);
        },
        error: function(){
            alert("rule not found, ruleId is: " + ruleId);
        }
    })
}
function initialize(rule) {
    $("#ruleId").val("" + rule.couponCalculateRuleId);
    $("#type").val(rule.getValueType);
    $("#ruleName").val(rule.couponCalculateRuleName);
    $("#description").val(rule.ruleDescription);
    $("#sellFrom").val(rule.sellPriceRangeFrom);
    $("#sellTo").val(rule.sellPriceRangeTo);
    $("#reserveFrom").val(rule.reservePriceRangeFrom);
    $("#reserveTo").val(rule.reservePriceRangeTo);
    $("#rateFrom").val(rule.commissionRateRangeFrom);
    $("#rateTo").val(rule.commissionRateRangeTo);
    $("#commissionFrom").val(rule.commissionRangeFrom);
    $("#commissionTo").val(rule.commissionRangeTo);
    $("#baseType").val(rule.setValueType);
    $("#valueType").val(rule.setValueStyle);
    $("#base").val(rule.setValue);
    $("#resultType").val(rule.calculateType);
    $("#multiple").val(rule.integerMultiples);
    $("#addend").val(rule.addend);
    $("#upperLimit").val(rule.upperLimit);
    $("#lowerLimit").val(rule.lowerLimit);
}
function reset() {
    $("#type").removeAttr("disabled");
//    $("#ruleName").removeAttr("disabled");
    $("#ruleId").val('');
    $("#type").val(0);
    $("#ruleName").val('');
    $("#description").val('');
    $("#sellFrom").val('');
    $("#sellTo").val('');
    $("#reserveFrom").val('');
    $("#reserveTo").val('');
    $("#rateFrom").val('');
    $("#rateTo").val('');
    $("#commissionFrom").val('');
    $("#commissionTo").val('');
    $("#baseType").val(0);
    $("#valueType").val(0);
    $("#base").val('');
    $("#resultType").val(0);
    $("#multiple").val('');
    $("#addend").val('');
    $("#upperLimit").val('');
    $("#lowerLimit").val('');
}