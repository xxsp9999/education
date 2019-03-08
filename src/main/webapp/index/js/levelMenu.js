/**
 * Created by LuoXueRui on 2016/12/30.
 */

$(function () {
  $.each(prov, function (k, p) {
    var option = "<option value='" + p.ProID + "'>" + p.name + "</option>";
    $("#selProvince").append(option);
  });

  $("#selProvince").change(function () {
    var selValue = $(this).val();
    $("#selCity option:gt(0)").remove();
    $.each(city, function (k, p) {
      if (p.ProID == selValue) {
        var option = "<option value='" + p.CityID + "'>" + p.name + "</option>";
        $("#selCity").append(option);
      }
    });

  });

  $("#selCity").change(function () {
    var selValue = $(this).val();
    $("#selDistrict option:gt(0)").remove();

    $.each(district, function (k, p) {
      if (p.CityID == selValue) {
        var option = "<option value='" + p.Id + "'>" + p.DisName + "</option>";
        $("#selDistrict").append(option);
      }
    });
  });
});