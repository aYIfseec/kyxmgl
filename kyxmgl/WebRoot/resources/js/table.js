	var tTD; //用来存储当前更改宽度的Table Cell,避免快速移动鼠标的问题   
	var table = document.getElementById("list-table");
	for (var j = 0; j < table.rows[0].cells.length; j++) {
		table.rows[0].cells[j].onmousedown = function() {
			//记录单元格   
			tTD = this;
			if (event.offsetX > tTD.offsetWidth - 10) {
				tTD.mouseDown = true;
				tTD.oldX = event.x;
				tTD.oldWidth = tTD.offsetWidth;
			}
			//记录Table宽度   
			//table = tTD; while (table.tagName != ‘TABLE') table = table.parentElement;   
			//tTD.tableWidth = table.offsetWidth;   
		};
		table.rows[0].cells[j].onmouseup = function() {
			//结束宽度调整   
			if (tTD == undefined)
				tTD = this;
			tTD.mouseDown = false;
			tTD.style.cursor = 'default';
		};
		table.rows[0].cells[j].onmousemove = function() {
			//更改鼠标样式   
			if (event.offsetX > this.offsetWidth - 10)
				this.style.cursor = 'col-resize';
			else
				this.style.cursor = 'default';
			//取出暂存的Table Cell   
			if (tTD == undefined)
				tTD = this;
			//调整宽度   
			if (tTD.mouseDown != null && tTD.mouseDown == true) {
				tTD.style.cursor = 'default';
				if (tTD.oldWidth + (event.x - tTD.oldX) > 0)
					tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
				//调整列宽   
				tTD.style.width = tTD.width;
				tTD.style.cursor = 'col-resize';
				//调整该列中的每个Cell   
				table = tTD;
				while (table.tagName != 'TABLE')
					table = table.parentElement;
				for (var j = 0; j < table.rows.length; j++) {
					table.rows[j].cells[tTD.cellIndex].width = tTD.width;
				}
				//调整整个表   
				//table.width = tTD.tableWidth + (tTD.offsetWidth – tTD.oldWidth);   
				//table.style.width = table.width;   
			}
		};
	}
	