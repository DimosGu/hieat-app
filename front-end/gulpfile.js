var gulp = require('gulp');
var sass = require('gulp-sass');
var minCss = require('gulp-minify-css');
var uglify = require('gulp-uglify');
var del = require('del');

gulp.task('scss', function () {
    gulp.src(['./src/scss/hieat.scss'])
        .pipe(sass())
        .pipe(gulp.dest('../public/main/css/'));
});
gulp.task('vendor:css', function () {
    gulp.src(['./vendor/css/**/*.css'])
        .pipe(sass())
        .pipe(gulp.dest('../public/vendor/css/'));
});
gulp.task('vendor:img', function () {
    gulp.src(['./vendor/img/**/*'])
        .pipe(gulp.dest('../public/vendor/img/'));
});

gulp.task('build', ['scss', 'vendor:css', 'vendor:img']);

gulp.task('watch', function () {
    gulp.watch('./src/scss/**/*.scss', ['scss']);
    gulp.watch('./vendor/css/**/*', ['vendor:css']);
    gulp.watch('./vendor/img/**/*', ['vendor:img']);
});

gulp.task('release', function () {
    gulp.src(['../public/vendor/**/*.css'])
        .pipe(minCss())
        .pipe(gulp.dest('../public/vendor/'));
    gulp.src(['../public/main/**/*.css'])
        .pipe(minCss())
        .pipe(gulp.dest('../public/main/'));
    gulp.src(['../public/vendor/**/*.js'])
        .pipe(uglify())
        .pipe(gulp.dest('../public/vendor/'));
});
