const canvas = document.getElementById('clock');
const ctx = canvas.getContext('2d');
const radius = canvas.width / 2;

ctx.translate(radius, radius); // Переносим начало координат в центр
const clockRadius = radius * 0.9;

// Функция для рисования циферблата
function drawClockFace() {
    ctx.beginPath();
    ctx.arc(0, 0, clockRadius, 0, 2 * Math.PI); // Рисуем окружность
    ctx.fillStyle = 'white';
    ctx.fill();
    ctx.lineWidth = 5;
    ctx.strokeStyle = '#FFF';
    ctx.stroke();

    // Рисуем метки
    for (let i = 0; i < 12; i++) {
        const angle = (i * Math.PI) / 6;
        const x = clockRadius * 0.85 * Math.cos(angle);
        const y = clockRadius * 0.85 * Math.sin(angle);

        ctx.beginPath();
        ctx.arc(x, y, 5, 0, 2 * Math.PI); // Маленькие точки на часах
        ctx.fillStyle = '#000';
        ctx.fill();
    }
}

// Функция для рисования стрелок
function drawHand(length, angle, width, color) {
    ctx.save();
    ctx.rotate(angle); // Поворачиваем контекст
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.lineTo(0, -length);
    ctx.lineWidth = width;
    ctx.strokeStyle = color;
    ctx.lineCap = 'round';
    ctx.stroke();
    ctx.restore();
}

// Функция для рисования времени
function drawTime() {
    const now = new Date();
    const seconds = now.getSeconds();
    const minutes = now.getMinutes();
    const hours = now.getHours();

    // Вычисляем углы
    const secondsAngle = ((seconds % 60) * Math.PI) / 30;
    const minutesAngle = ((minutes % 60) * Math.PI) / 30 + seconds * Math.PI / 1800;
    const hoursAngle = ((hours % 12) * Math.PI) / 6 + (minutes * Math.PI) / 360;

    // Рисуем часовую стрелку
    drawHand(clockRadius * 0.5, hoursAngle, 8, '#260000');

    // Рисуем минутную стрелку
    drawHand(clockRadius * 0.7, minutesAngle, 6, '#420101');

    // Рисуем секундную стрелку
    drawHand(clockRadius * 0.9, secondsAngle, 3, '#9b2b02');

}

// Основная функция для обновления часов
function drawClock() {
    ctx.clearRect(-radius, -radius, canvas.width, canvas.height); // Очищаем холст
    drawClockFace(); // Рисуем циферблат
    drawTime(); // Рисуем стрелки
}

// Обновляем часы каждые 6 секунд
setInterval(drawClock, 6000);
drawClock(); // Рисуем часы сразу при загрузке
