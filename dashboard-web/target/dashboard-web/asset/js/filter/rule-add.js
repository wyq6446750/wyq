/**
 * Created by wei.feng on 16-8-25.
 */
function addCity() {
    $("#city option:selected").each(function() {
        $("#ck_city").append($(this).clone())
        $(this).removeAttr("selected");
    });
    getDistrictAndArea();
}
function deleteCity() {
    $("#ck_city option:selected").each(function() {
        $(this).remove();
    });
    getDistrictAndArea();
}
function addDistrict() {
    $("#district option:selected").each(function() {
        $("#ck_district").append($(this).clone())
        $(this).removeAttr("selected");
    })
}
function deleteDistrict() {
    $("#ck_district option:selected").each(function() {
        $(this).remove();
    })
}
function addArea() {
    $("#area option:selected").each(function() {
        $("#ck_area").append($(this).clone())
        $(this).removeAttr("selected");
    })
}
function deleteArea() {
    $("#ck_area option:selected").each(function() {
        $(this).remove();
    })
}
function addGroup() {
    $("#group option:selected").each(function() {
        $("#ck_group").append($(this).clone())
        $(this).removeAttr("selected");
    })
}
function deleteGroup() {
    $("#ck_group option:selected").each(function() {
        $(this).remove();
    })
}
function addSupplier() {
    $("#supplier option:selected").each(function() {
        $("#ck_supplier").append($(this).clone())
        $(this).removeAttr("selected");
    })
}
function deleteSupplier() {
    $("#ck_supplier option:selected").each(function() {
        $(this).remove();
    })
}

function getCity() {
    var dictKey = $("#province option:selected").attr("dict-key");
    $("#city").empty();
    if ($("#province option:selected").val()=="") {
        return;
    }
    var parentIds = new Array();
    parentIds.push(dictKey);
    if (parentIds.length == 0) {
        return;
    }
    getChildren("CITY", parentIds, refreshCity);

}
function refreshCity(dicts) {
    for (var index in dicts) {
        html = "<option dict-id=" + dicts[index].id + " dict-key=" + dicts[index].dictKey + ">" + dicts[index].dictValue + "</option>"
        $("#city").append(html)
    }
}
function getDistrictAndArea() {
    $("#district").empty();
    $("#area").empty();
    var parentIds = new Array();
    $("#ck_city option").each(function() {
        parentIds.push($(this).attr("dict-key"));
    });
    if (parentIds.length == 0) {
        return;
    }
    getChildren("AREA", parentIds, refreshDistrict);
    getChildren("ADMIN_AREA", parentIds, refreshArea);

}
function refreshDistrict(dicts) {
    for (var index in dicts) {
        html = "<option dict-id=" + dicts[index].id + " dict-key=" + dicts[index].dictKey + ">" + dicts[index].dictValue + "</option>"
        $("#district").append(html)
    }
}
function refreshArea(dicts) {
    for (var index in dicts) {
        html = "<option dict-id=" + dicts[index].id + " dict-key=" + dicts[index].dictKey + ">" + dicts[index].dictValue + "</option>"
        $("#area").append(html)
    }
}
function getChildren(dictType, parentIds, callback) {
    $.ajax({
        type: "POST",
        url: "/dictionary/getChildren",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"dictType": dictType, "parentIds": parentIds},
        traditional: true,
        dataType: "json",
        success: function(result){
            callback(result);
        },
        error: function(){
            alert("error message");
        }

    });
}

function searchKeyword() {
    $("#city option").each(function() {
        var keyword = $("#keyword").val();
        var value = $(this).val();
        if(value.indexOf(keyword) < 0) {
            $(this).addClass('hide');
        } else {
            $(this).removeClass('hide');
        }
    });
    $("#group option").each(function() {
        var keyword = $("#keyword").val();
        var value = $(this).val();
        if(value.indexOf(keyword) < 0) {
            $(this).addClass('hide');
        } else {
            $(this).removeClass('hide');
        }
    });
    $("#supplier option").each(function() {
        var keyword = $("#keyword").val();
        var value = $(this).val();
        if(value.indexOf(keyword) < 0) {
            $(this).addClass('hide');
        } else {
            $(this).removeClass('hide');
        }
    });
}