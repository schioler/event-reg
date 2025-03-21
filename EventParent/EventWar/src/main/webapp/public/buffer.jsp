   <select name="unit" id="unit">
                        <c:choose>
                           <c:when test="${empty sesSelectedUnit }">
                              <option selected value="---">Choose UNIT</option>
                              <c:forEach var="unit" items="${sesSelectableUnits}">
                                 <option value="${unit}">${unit}</option>
                              </c:forEach>
                           </c:when>
                           <c:otherwise>
                              <c:forEach var="unit" items="${sesSelectableUnits}">
                                 <c:choose>
                                    <c:when test="${unit == sesSelectedUnit }">
                                       <option selected value="${unit}">${unit}</option>
                                    </c:when>
                                    <c:otherwise>
                                       <option value="${unit}">${unit}</option>
                                    </c:otherwise>
                                 </c:choose>
                              </c:forEach>
                           </c:otherwise>
                        </c:choose>
                       </select>